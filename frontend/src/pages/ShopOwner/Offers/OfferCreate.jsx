import { useState, useEffect } from 'react'
import api from '../../../api/api'
import { useNavigate } from 'react-router-dom'

export default function OfferCreate(){
  const [items, setItems] = useState([])
  const [itemId, setItemId] = useState('')
  const [dealName, setDealName] = useState('')
  const [discountType, setDiscountType] = useState('PERCENTAGE')
  const [discountValue, setDiscountValue] = useState(10)
  const [startTime, setStartTime] = useState('')
  const [endTime, setEndTime] = useState('')
  const navigate = useNavigate()

  useEffect(()=>{
    const ownerId = localStorage.getItem('userId')
    if(!ownerId) return
    // get owner's shops then items: for simplicity assume /items/shop/{shopId} exists, otherwise fetch all and filter
    api.get(`/shops?ownerId=${ownerId}`).then(async res=>{
      const shops = res.data
      // fetch items for each shop
      const allItems = []
      for(const s of shops){
        const r = await api.get(`/items/shop/${s.id}`)
        r.data.forEach(i=>allItems.push(i))
      }
      setItems(allItems)
    }).catch(console.error)
  },[])

  async function createOffer(e){
    e.preventDefault()
    try{
      await api.post(`/offers?itemId=${itemId}`, { dealName, discountType, discountValue, startTime, endTime })
      alert('Offer created')
      navigate('/owner')
    }catch(err){
      alert('Failed to create offer')
      console.error(err)
    }
  }

  return (
    <div className="container mt-4 col-md-8">
      <h4>Create Discount Offer</h4>
      <form onSubmit={createOffer}>
        <select className="form-select mb-2" value={itemId} onChange={e=>setItemId(e.target.value)}>
          <option value="">Select Item</option>
          {items.map(i=> <option key={i.id} value={i.id}>{i.itemName} ({i.shop?.shopName || 'shop'})</option>)}
        </select>
        <input className="form-control mb-2" placeholder="Deal Name" value={dealName} onChange={e=>setDealName(e.target.value)} />
        <label>Discount</label>
        <select className="form-select mb-2" value={discountType} onChange={e=>setDiscountType(e.target.value)}>
          <option value="PERCENTAGE">PERCENTAGE</option>
          <option value="FIXED">FIXED</option>
        </select>
        <input className="form-control mb-2" type="number" placeholder="Discount value" value={discountValue} onChange={e=>setDiscountValue(Number(e.target.value))} />
        <label>Start Time</label>
        <input className="form-control mb-2" type="datetime-local" value={startTime} onChange={e=>setStartTime(e.target.value)} />
        <label>End Time</label>
        <input className="form-control mb-2" type="datetime-local" value={endTime} onChange={e=>setEndTime(e.target.value)} />
        <button className="btn btn-primary">Create Offer</button>
      </form>
    </div>
  )
}