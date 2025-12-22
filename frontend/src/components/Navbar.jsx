import { Link, useNavigate } from 'react-router-dom'

export default function Navbar(){
  const navigate = useNavigate()
  const role = localStorage.getItem('role')
  const name = localStorage.getItem('name')

  function logout(){
    localStorage.clear()
    navigate('/')
  }

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="container">
        <Link className="navbar-brand" to="/">LocalShopper</Link>
        <div className="collapse navbar-collapse">
          <ul className="navbar-nav me-auto">
            {role === 'CUSTOMER' && (
              <li className="nav-item"><Link className="nav-link" to="/customer">Home</Link></li>
            )}
            {role === 'SHOP_OWNER' && (
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
            {name ? (
              <>
                <li className="nav-item nav-link">{name}</li>
                <li className="nav-item"><button className="btn btn-outline-secondary" onClick={logout}>Logout</button></li>
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