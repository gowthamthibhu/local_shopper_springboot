import { useState, useEffect } from 'react'
import api from '../api/api'
import { useNavigate } from 'react-router-dom'

export default function ItemCreate(){
  const [shops, setShops] = useState([])
  const [shopId, setShopId] = useState('')
  const [itemName, setItemName] = useState('')
  const [description, setDescription] = useState('')
  const [price, setPrice] = useState(0)
  const [quantity, setQuantity] = useState(1)
  const navigate = useNavigate()

  useEffect(()=>{
    const ownerId = localStorage.getItem('userId')
    if(!ownerId) return
    api.get(`/shops?ownerId=${ownerId}`).then(res=>setShops(res.data)).catch(console.error)
  },[])

  async function addItem(e){
    e.preventDefault()
    try{
      await api.post(`/items?shopId=${shopId}`, { itemName, description, price, quantity })
      alert('Item added')
      navigate('/owner')
    }catch(err){
      alert('Failed to add item')
      console.error(err)
    }
  }

  return (
    <div className="container mt-4 col-md-6">
      <h4>Add Item</h4>
      <form onSubmit={addItem}>
        <select className="form-select mb-2" value={shopId} onChange={e=>setShopId(e.target.value)}>
          <option value="">Select Shop</option>
          {shops.map(s=> <option key={s.id} value={s.id}>{s.shopName}</option>)}
        </select>
        <input className="form-control mb-2" placeholder="Item name" value={itemName} onChange={e=>setItemName(e.target.value)} />
        <input className="form-control mb-2" placeholder="Description" value={description} onChange={e=>setDescription(e.target.value)} />
        <input className="form-control mb-2" type="number" placeholder="Price" value={price} onChange={e=>setPrice(Number(e.target.value))} />
        <input className="form-control mb-2" type="number" placeholder="Quantity" value={quantity} onChange={e=>setQuantity(Number(e.target.value))} />
        <button className="btn btn-primary">Add Item</button>
      </form>
    </div>
  )
}