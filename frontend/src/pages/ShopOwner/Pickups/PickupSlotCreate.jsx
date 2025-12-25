import { useState, useEffect } from 'react'
import api from '../../../api/api'
import { useNavigate } from 'react-router-dom'

export default function PickupSlotCreate(){
  const [shopId, setShopId] = useState('')
  const [shops, setShops] = useState([])
  const [startTime, setStartTime] = useState('')
  const [endTime, setEndTime] = useState('')
  const [maxPickups, setMaxPickups] = useState(5)
  const navigate = useNavigate()

  useEffect(()=>{
    const ownerId = localStorage.getItem('userId')
    if(!ownerId) return
    api.get(`/shops?ownerId=${ownerId}`).then(res=>setShops(res.data)).catch(console.error)
  },[])

  async function createSlot(e){
    e.preventDefault()
    try{
      await api.post(`/pickup-slots?shopId=${shopId}`, { startTime, endTime, maxPickups })
      alert('Slot created')
      navigate('/owner')
    }catch(err){
      alert('Failed to create slot')
      console.error(err)
    }
  }

  return (
    <div className="container mt-4 col-md-6">
      <h4>Create Pickup Slot</h4>
      <form onSubmit={createSlot}>
        <select className="form-select mb-2" value={shopId} onChange={e=>setShopId(e.target.value)}>
          <option value="">Select Shop</option>
          {shops.map(s=> <option key={s.id} value={s.id}>{s.shopName}</option>)}
        </select>
        <label>Start Time</label>
        <input className="form-control mb-2" type="datetime-local" value={startTime} onChange={e=>setStartTime(e.target.value)} />
        <label>End Time</label>
        <input className="form-control mb-2" type="datetime-local" value={endTime} onChange={e=>setEndTime(e.target.value)} />
        <input className="form-control mb-2" type="number" value={maxPickups} onChange={e=>setMaxPickups(Number(e.target.value))} />
        <button className="btn btn-primary">Create Slot</button>
      </form>
    </div>
  )
}