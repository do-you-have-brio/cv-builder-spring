interface RouteNav {
  label: string;
  path: string;
}

export const ROUTER_NAVIGATION: RouteNav[] = [
  { label: "My resumes", path: "/resumes" },
  { label: "My applications", path: "/applications" },
  { label: "Profile", path: "/profile" },
];
