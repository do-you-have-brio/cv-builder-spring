import { Outlet } from "react-router-dom";

export const AuthLayout = () => {
  return (
    <div className="flex-1 grid place-items-center p-4">
      <Outlet />
    </div>
  );
};
