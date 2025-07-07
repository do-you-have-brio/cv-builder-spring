import { z } from "zod";

export const createJobSchema = z.object({
  id: z.string().uuid("Invalid UUID format"),
  title: z.string().min(1, "Title is required"),
  company: z.string().min(1, "Company name is required"),
  description: z.string().min(1, "Description is required"),
  startDate: z.date({
    required_error: "Start date is required",
    invalid_type_error: "Invalid date format",
  }),
  endDate: z.date({
    required_error: "End date is required",
    invalid_type_error: "Invalid date format",
  }),
});
export type CreateJobSchema = z.infer<typeof createJobSchema>;

export const jobSchema = z.object({
  id: z.string().uuid("Invalid UUID format"),
  title: z.string().min(1, "Title is required"),
  company: z.string().min(1, "Company is required"),
  description: z.string().min(1, "Description is required"),
  startDate: z.date({
    required_error: "Start date is required",
    invalid_type_error: "Invalid date format",
  }),
  endDate: z.date({
    required_error: "End date is required",
    invalid_type_error: "Invalid date format",
  }),
  userId: z.string().uuid("Invalid user ID format"),
  createdAt: z.date().optional(),
  updatedAt: z.date().optional(),
});
export type Job = z.infer<typeof jobSchema>;
