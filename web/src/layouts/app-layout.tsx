import { Outlet } from "react-router-dom";

export const AppLayout = () => {
  return (
    <div className="flex-1 ">
      <header>header here</header>
      <Outlet />
    </div>
  );
};
