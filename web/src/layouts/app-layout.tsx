import { UserNav } from "@/components/user-nav";
import { cn } from "@/lib/utils";
import { ROUTER_NAVIGATION } from "@/routes/navigation";
import { Link, NavLink, Outlet } from "react-router-dom";

export const AppLayout = () => {
  return (
    <div className="flex-1">
      <div className="border-b">
        <header className="mx-auto flex items-center justify-between max-w-3xl px-4 py-2">
          <Link to="/">
            <h1>CVBuilder</h1>
          </Link>

          <nav className="flex items-center gap-4">
            {ROUTER_NAVIGATION.map((nav) => (
              <NavLink
                to={nav.path}
                className={({ isActive }) =>
                  cn(
                    "cursor-pointer inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-md text-sm font-medium transition-all disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 shrink-0 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive",
                    {
                      "text-primary": isActive,
                      "text-muted-foreground": !isActive,
                    }
                  )
                }
              >
                {nav.label}
              </NavLink>
            ))}
          </nav>
          <UserNav />
        </header>
      </div>

      <div className="mx-auto flex items-center justify-between max-w-3xl px-4 py-2">
        <Outlet />
      </div>
    </div>
  );
};
