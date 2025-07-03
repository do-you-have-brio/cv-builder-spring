import { UserNav } from "@/components/user-nav";
import { Link, Outlet } from "react-router-dom";

export const AppLayout = () => {
  return (
    <div className="flex-1">
      <div className="border-b">
        <header className="mx-auto flex items-center justify-between max-w-3xl px-4 py-2">
          <Link to="/">
            <h1>CVBuilder</h1>
          </Link>
          <UserNav />
        </header>
      </div>

      <div className="mx-auto flex items-center justify-between max-w-3xl px-4 py-2">
        <Outlet />
      </div>
    </div>
  );
};
