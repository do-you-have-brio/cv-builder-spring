import { useAuthStore } from "@/stores/auth-store";
import * as React from "react";
import { Navigate, Outlet, useLocation } from "react-router-dom";

interface WithSessionProps {
  children?: React.ReactNode;
  redirectToSignIn?: boolean;
}

export const WithSession = (props: WithSessionProps) => {
  const location = useLocation();
  const { session } = useAuthStore();

  const shouldRedirect = props.redirectToSignIn && !session;
  return shouldRedirect ? (
    <Navigate to="/auth/sign-in" state={{ from: location }} replace />
  ) : session ? (
    <React.Fragment>
      <Outlet />
      {props.children}
    </React.Fragment>
  ) : null;
};
