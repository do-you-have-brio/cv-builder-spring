import { useAuthStore } from "@/stores/auth-store";
import { useNavigate } from "react-router-dom";

export const SignInPage = () => {
  const { setSession } = useAuthStore();
  const navigate = useNavigate();

  const handleSignIn = () => {
    setSession(true);
    navigate("/", { replace: true });
  };

  return (
    <div className="flex flex-col">
      SignIn
      <button onClick={handleSignIn}>sing in</button>
    </div>
  );
};
