import { Link } from 'react-router-dom'

export default function OwnerHome(){
  return (
    <div className="container mt-4">
      <h3>Owner Dashboard</h3>
      <p>Use the links to create shops, items, offers, and pickup slots.</p>
      <div className="list-group">
        <Link className="list-group-item" to="/owner/my-shops">My Shops</Link>
        <Link className="list-group-item" to="/owner/create-shop">Create Shop</Link>
        <Link className="list-group-item" to="/owner/create-item">Add Item</Link>
        <Link className="list-group-item" to="/owner/create-offer">Add Discount Offer</Link>
        <Link className="list-group-item" to="/owner/create-slot">Add Pickup Slot</Link>
      </div>
    </div>
  )
}