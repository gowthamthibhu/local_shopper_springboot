import { useState } from "react";
import api from "../api/api";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const login = async () => {
    try {
      const res = await api.post("/auth/login", {
        email: email.trim(),
        password: password.trim()
      });
      const { role, userId } = res.data;

      localStorage.setItem("userId", userId);
      localStorage.setItem("role", role);

      if (role === "CUSTOMER") {
        window.location.href = "/customer";
      } else {
        window.location.href = "/owner";
      }
    } catch {
      alert("Invalid credentials");
    }
  };

  return (
    <div className="container mt-5 col-4">
      <h3 className="mb-3">Login</h3>

      <input
        className="form-control mb-2"
        placeholder="Email"
        onChange={(e) => setEmail(e.target.value)}
      />

      <input
        type="password"
        className="form-control mb-2"
        placeholder="Password"
        onChange={(e) => setPassword(e.target.value)}
      />

      <button className="btn btn-primary w-100" onClick={login}>
        Login
      </button>
    </div>
  );
}

export default Login;
