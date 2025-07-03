import { useNavigate } from "react-router-dom";

export const NotFoundPage = () => {
  const navigate = useNavigate();

  const handleReturnHome = () => navigate("/", { replace: true });

  return (
    <div>
      <h1>404</h1>
      <p>Page not found</p>
      <button onClick={handleReturnHome}>Return home</button>
    </div>
  );
};
