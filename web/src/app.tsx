import { ThemeProvider } from "@/contexts/theme-provider";
import { router } from "@/routes/router";
import { RouterProvider } from "react-router-dom";

export const App = () => {
  return (
    <ThemeProvider>
      <RouterProvider router={router} />
    </ThemeProvider>
  );
};
