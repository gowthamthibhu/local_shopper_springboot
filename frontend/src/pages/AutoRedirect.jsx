import { Navigate } from "react-router-dom";

export default function AutoRedirect() {
  const role = localStorage.getItem("role");

  if (role === "CUSTOMER") return <Navigate to="/customer" />;
  if (role === "SHOP_OWNER") return <Navigate to="/owner" />;

  // not logged in
  return <Navigate to="/login" />;
}
