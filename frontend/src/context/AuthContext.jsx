import { createContext, useState, useEffect } from "react";

export const AuthContext = createContext();

export default function AuthProvider({ children }) {

  const [user, setUser] = useState({
    id: localStorage.getItem("userId"),
    name: localStorage.getItem("name"),
    role: localStorage.getItem("role"),
  });

  useEffect(() => {
    if (user?.id) {
      localStorage.setItem("userId", user.id);
      localStorage.setItem("name", user.name);
      localStorage.setItem("role", user.role);
    }
  }, [user]);

  return (
    <AuthContext.Provider value={{ user, setUser }}>
      {children}
    </AuthContext.Provider>
  );
}
