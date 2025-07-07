import { z } from "zod";

export const createResumeSchema = z.object({
  userId: z.string().uuid("Invalid user ID format"),
  name: z.string().min(1, "Resume name is required"),
  link: z.string().min(1, "Link is required").url("Invalid URL format"),
});
export type CreateResumeSchema = z.infer<typeof createResumeSchema>;

export const resumeSchema = z.object({
  id: z.string().uuid("Invalid UUID format"),
  name: z.string().min(1, "Name is required"),
  link: z.string().min(1, "Link is required").url("Invalid URL format"),
  userId: z.string().uuid("Invalid user ID format"),
});
export type Resume = z.infer<typeof resumeSchema>;
