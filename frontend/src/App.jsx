import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import CustomerHome from "./pages/CustomerHome";
import OwnerHome from "./pages/OwnerHome";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/customer" element={<CustomerHome />} />
        <Route path="/owner" element={<OwnerHome />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
