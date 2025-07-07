import type { SignUpResponseSchema, SignUpSchema } from "@/@types/schemas/auth";
import { authServerInstance } from "@/lib/axios";
import { useMutation } from "@tanstack/react-query";

export const useSignUp = () => {
  return useMutation<SignUpResponseSchema, Error, SignUpSchema>({
    mutationFn: async (data) => {
      const response = await authServerInstance.post<SignUpResponseSchema>(
        "/register",
        data
      );
      return response.data;
    },
  });
};
