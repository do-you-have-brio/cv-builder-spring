import { z } from "zod";

export const createEducationSchema = z.object({
  id: z.string().uuid("Invalid education ID format"),
  institution: z.string().min(1, "Institution name is required"),
  degree: z.string().min(1, "Degree is required"),
  fieldOfStudy: z.string().min(1, "Field of study is required"),
  startDate: z.date({
    required_error: "Start date is required",
    invalid_type_error: "Invalid date format",
  }),
  endDate: z.date({
    required_error: "End date is required",
    invalid_type_error: "Invalid date format",
  }),
  description: z.string().min(1, "Description is required"),
});
export type CreateEducationSchema = z.infer<typeof createEducationSchema>;

export const educationSchema = z.object({
  id: z.string().uuid("Invalid UUID format"),
  degree: z.string().min(1, "Degree is required"),
  institution: z.string().min(1, "Institution is required"),
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
export type Education = z.infer<typeof educationSchema>;
