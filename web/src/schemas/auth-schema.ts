import { z } from "zod";

export const signInSchema = z.object({
  email: z.string().email({ message: "Formato de email inválido" }),
  password: z
    .string()
    .min(8, { message: "A senha deve ter no mínimo 8 caracteres" }),
});
export type SignInSchema = z.infer<typeof signInSchema>;

export const signUpSchema = z
  .object({
    name: z.string().min(1, { message: "O nome é obrigatório" }),
    email: z.string().email({ message: "Formato de email inválido" }),
    password: z
      .string()
      .min(8, { message: "A senha deve ter no mínimo 8 caracteres" }),
    confirmPassword: z.string().min(8, {
      message: "A confirmação de senha deve ter no mínimo 8 caracteres",
    }),
  })
  .refine(({ password, confirmPassword }) => password === confirmPassword, {
    message: "As senhas não coincidem",
  });
export type SignUpSchema = z.infer<typeof signUpSchema>;
