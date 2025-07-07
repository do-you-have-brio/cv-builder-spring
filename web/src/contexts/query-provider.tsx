import {
  MutationCache,
  QueryCache,
  QueryClient,
  QueryClientProvider,
} from "@tanstack/react-query";
import { toast } from "sonner";

interface QueryProviderProps {
  children: React.ReactNode;
}

export const QueryProvider = (props: QueryProviderProps) => {
  /**
   * Handle error here
   * @todo improve toast, add option to retry
   * @see https://tkdodo.eu/blog/react-query-error-handling
   */
  const handleDisplayErrorToast = (error: string) => {
    console.error(error);
    toast("Oops, something went wrong!", { description: error });
  };

  /*
   * For more information on React Query QueryClient, refer to the official documentation:
   * @see: https://tanstack.com/query/latest/docs/react/reference/QueryClient
   */
  const queryClient = new QueryClient({
    defaultOptions: {
      queries: { retry: 3 },
      mutations: { retry: 3 },
    },

    queryCache: new QueryCache({
      onError: (error, query) => {
        console.warn(`Error in query ${query.queryKey}`, error);
        handleDisplayErrorToast(error?.message ?? "Unknown error");
      },
    }),
    mutationCache: new MutationCache({
      onError: (error, _, __, mutation) => {
        console.warn(`Error in mutation ${mutation.mutationId}`, error);
        handleDisplayErrorToast(error?.message ?? "Unknown error");
      },
    }),
  });

  return <QueryClientProvider client={queryClient} {...props} />;
};
