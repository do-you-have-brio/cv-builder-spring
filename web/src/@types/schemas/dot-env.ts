import { z } from "zod";

export const dotEnvSchema = z.object({
  VITE_AUTH_SERVER_URL: z.string().url(),
  VITE_API_SERVER_URL: z.string().url(),
});
export type DotEnv = z.infer<typeof dotEnvSchema>;
