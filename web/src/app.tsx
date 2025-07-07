import { Toaster } from "@/components/ui/sonner";
import { QueryProvider } from "@/contexts/query-provider";
import { ThemeProvider } from "@/contexts/theme-provider";
import { router } from "@/routes/router";
import { RouterProvider } from "react-router-dom";

export const App = () => {
  return (
    <ThemeProvider>
      <QueryProvider>
        <RouterProvider router={router} />
        <Toaster />
      </QueryProvider>
    </ThemeProvider>
  );
};
