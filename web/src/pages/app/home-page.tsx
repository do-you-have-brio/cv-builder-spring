import { useAuthStore } from "@/stores/auth-store";

export const HomePage = () => {
  const { clearSession } = useAuthStore();

  const handleSignOut = () => clearSession();

  return (
    <div className="flex flex-col">
      HomePage
      <button onClick={handleSignOut}>sing out</button>
    </div>
  );
};
