import type { SessionSchema, SignInSchema } from "@/@types/schemas/auth";
import { authServerInstance } from "@/lib/axios";
import { useMutation } from "@tanstack/react-query";

export const useSignIn = () => {
  return useMutation<SessionSchema, Error, SignInSchema>({
    mutationFn: async (credentials) => {
      const response = await authServerInstance.post<SessionSchema>(
        "/login",
        credentials
      );
      return response.data;
    },
  });
};
