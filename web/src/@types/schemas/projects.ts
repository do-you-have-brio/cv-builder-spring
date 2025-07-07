import { z } from "zod";

export const createProjectsSchema = z.object({
  id: z.string().uuid("Invalid project ID format"),
  name: z.string().min(1, "Project name is required"),
  description: z.string().min(1, "Description is required"),
});
export type CreateProjectsSchema = z.infer<typeof createProjectsSchema>;

export const projectsSchema = z.object({
  id: z.string().uuid("Invalid UUID format"),
  name: z.string().min(1, "Name is required"),
  description: z.string().min(1, "Description is required"),
  userId: z.string().uuid("Invalid user ID format"),
});
export type Projects = z.infer<typeof projectsSchema>;
