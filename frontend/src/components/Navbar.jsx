import { Link, useNavigate } from 'react-router-dom'
import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";



export default function Navbar(){
  const navigate = useNavigate()
  const { user, setUser } = useContext(AuthContext);
  function logout() {
    localStorage.clear();
    setUser({ name: null, role: null });
    navigate("/");
  }


  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="container">
        <Link className="navbar-brand" to="/">LocalShopper</Link>
        <div className="collapse navbar-collapse">
          <ul className="navbar-nav me-auto">
            {user.role === 'CUSTOMER' && (
              <li className="nav-item"><Link className="nav-link" to="/customer">Home</Link></li>
            )}
            {user.role === 'SHOP_OWNER' && (
              <>
                <li className="nav-item"><Link className="nav-link" to="/owner">Dashboard</Link></li>
                <li className="nav-item"><Link className="nav-link" to="/owner/create-shop">Create Shop</Link></li>
                <li className="nav-item"><Link className="nav-link" to="/owner/create-item">Add Item</Link></li>
                <li className="nav-item"><Link className="nav-link" to="/owner/create-offer">Add Offer</Link></li>
                <li className="nav-item"><Link className="nav-link" to="/owner/create-slot">Add Slot</Link></li>
              </>
            )}
          </ul>

          <ul className="navbar-nav ms-auto">
          {user?.name ? (
            <>
              <li className="nav-item nav-link">{user.name}</li>
              <li className="nav-item">
                <button className="btn btn-outline-secondary" onClick={logout}>
                  Logout
                </button>
              </li>
            </>
          ) : (
            <li className="nav-item"><Link className="nav-link" to="/">Login</Link></li>
          )}
          </ul>
        </div>
      </div>
    </nav>
  )
}