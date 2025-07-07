import { Outlet } from "react-router-dom";

export const RootLayout = () => {
  return (
    <div className="flex flex-col min-h-screen relative no-scrollbar bg-background text-foreground max-w-screen max-h-screen overflow-hidden">
      <Outlet />
    </div>
  );
};
