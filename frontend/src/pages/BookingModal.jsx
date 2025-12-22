import { useEffect, useState } from 'react'
import api from '../api/api'

export default function BookingModal({ offer, onClose }){
  const [slots, setSlots] = useState([])
  const [slotId, setSlotId] = useState(null)
  const [quantity, setQuantity] = useState(1)

  useEffect(()=>{
    if(!offer) return
    const shopId = offer.shopId
    if(!shopId) return
    api.get(`/pickup-slots/shop/${shopId}`).then(res=>setSlots(res.data)).catch(console.error)
  },[offer])

  async function book(){
    const userId = localStorage.getItem('userId')
    if(!userId) return alert('Please login')
    if(!slotId) return alert('Select a pickup slot')

    try{
      const res = await api.post('/bookings', { userId: Number(userId), offerId: offer.id, pickupSlotId: Number(slotId), quantity })
      alert('Booked! Booking id: ' + res.data.bookingId)
      onClose()
    }catch(err){
      alert(err?.response?.data || 'Booking failed')
      console.error(err)
    }
  }

  if(!offer) return null

  return (
    <div className="modal d-block" tabIndex={-1} role="dialog">
      <div className="modal-dialog" role="document">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title">Book: {offer.dealName}</h5>
            <button type="button" className="btn-close" onClick={onClose}></button>
          </div>
          <div className="modal-body">
            <div className="mb-2">Item: {offer.item?.itemName}</div>
            <div className="mb-2">
              <label>Pickup Slot</label>
              <select className="form-select" value={slotId || ''} onChange={e=>setSlotId(e.target.value)}>
                <option value="">Select slot</option>
                {slots.map(s=> (
                  <option key={s.id} value={s.id}>{new Date(s.startTime).toLocaleString()} - {s.currentPickups}/{s.maxPickups}</option>
                ))}
              </select>
            </div>
            <div className="mb-2">
              <label>Quantity</label>
              <input type="number" className="form-control" value={quantity} onChange={e=>setQuantity(Number(e.target.value))} min={1} />
            </div>
          </div>
          <div className="modal-footer">
            <button className="btn btn-secondary" onClick={onClose}>Cancel</button>
            <button className="btn btn-primary" onClick={book}>Confirm Booking</button>
          </div>
        </div>
      </div>
    </div>
  )
}
