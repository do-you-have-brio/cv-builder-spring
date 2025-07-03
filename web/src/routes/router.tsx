import { AppLayout } from "@/layouts/app-layout";
import { AuthLayout } from "@/layouts/auth-layout";
import { RootLayout } from "@/layouts/root-layout";
import { WithSession } from "@/middlewares/with-session";
import { ApplicationsPage } from "@/pages/app/applications-page";
import { ProfilePage } from "@/pages/app/profile-page";
import { ResumesPage } from "@/pages/app/resumes-page";
import { SignInPage } from "@/pages/auth/sign-in-page";
import { SignUpPage } from "@/pages/auth/sign-up-page";
import { NotFoundPage } from "@/pages/common/not-found-page";
import {
  createBrowserRouter,
  Navigate,
  type RouteObject,
} from "react-router-dom";

const publicRoutes: RouteObject[] = [
  {
    element: <AuthLayout />,
    path: "/auth",
    children: [
      { path: "sign-in", element: <SignInPage /> },
      { path: "sign-up", element: <SignUpPage /> },
    ],
  },
  { path: "*", element: <NotFoundPage /> },
];

const privateRoutes: RouteObject[] = [
  {
    element: <AppLayout />,
    children: [
      { path: "/", element: <Navigate to="/resumes" />, index: true },
      { path: "/profile", element: <ProfilePage /> },
      { path: "/resumes", element: <ResumesPage /> },
      { path: "/applications", element: <ApplicationsPage /> },
    ],
  },
];

const routes: RouteObject[] = [
  {
    element: <RootLayout />,
    children: [
      ...publicRoutes,
      { element: <WithSession redirectToSignIn />, children: privateRoutes },
    ],
  },
];

export const router = createBrowserRouter(routes);
