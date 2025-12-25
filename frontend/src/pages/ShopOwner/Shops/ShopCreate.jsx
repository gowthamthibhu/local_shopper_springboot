import { useState, useEffect } from 'react'
import api from '../../../api/api'
import { useNavigate } from 'react-router-dom'

export default function ShopCreate(){
  const [shopName, setShopName] = useState('')
  const [address, setAddress] = useState('')
  const [city, setCity] = useState('')
  const [area, setArea] = useState('')

  // ⭐ NEW
  const [openingTime, setOpeningTime] = useState('')
  const [closingTime, setClosingTime] = useState('')
  const [open, setOpen] = useState(false)

  const navigate = useNavigate()

  async function createShop(e){
    e.preventDefault()
    const ownerId = localStorage.getItem('userId')

    try{
      await api.post(`/shops?ownerId=${ownerId}`, {
        shopName,
        address,
        city,
        area,

        // ⭐ send new fields
        open,
        openingTime,
        closingTime
      })

      alert('Shop created')
      navigate('/owner')
    }catch(err){
      alert('Failed to create shop')
      console.error(err)
    }
  }

  return (
    <div className="container mt-4 col-md-6">
      <h4>Create Shop</h4>

      <form onSubmit={createShop}>

        <input className="form-control mb-2" placeholder="Shop Name"
          value={shopName} onChange={e=>setShopName(e.target.value)} />

        <input className="form-control mb-2" placeholder="Address"
          value={address} onChange={e=>setAddress(e.target.value)} />

        <input className="form-control mb-2" placeholder="City"
          value={city} onChange={e=>setCity(e.target.value)} />

        <input className="form-control mb-2" placeholder="Area"
          value={area} onChange={e=>setArea(e.target.value)} />

        {/* ⭐ NEW FIELDS */}

        <label>Opening Time</label>
        <input type="time" className="form-control mb-2"
          value={openingTime} onChange={e=>setOpeningTime(e.target.value)} />

        <label>Closing Time</label>
        <input type="time" className="form-control mb-2"
          value={closingTime} onChange={e=>setClosingTime(e.target.value)} />

        <div className="form-check mb-2">
          <input
            className="form-check-input"
            type="checkbox"
            checked={open}
            onChange={e=>setOpen(e.target.checked)}
          />
          <label className="form-check-label">Currently Open</label>
        </div>

        <button className="btn btn-primary">Create</button>
      </form>
    </div>
  )
}
