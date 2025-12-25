import { useEffect, useState } from 'react'
import api from '../../api/api'
import BookingModal from './Bookings/BookingModal'

export default function CustomerHome(){
  const [offers, setOffers] = useState([])
  const [selectedOffer, setSelectedOffer] = useState(null)
  const [showModal, setShowModal] = useState(false)

  useEffect(()=>{
    api.get('/offers/active').then(res=>setOffers(res.data)).catch(console.error)
  },[])

  function openBooking(offer){
    setSelectedOffer(offer)
    setShowModal(true)
  }
  useEffect(()=>{
    api.get('/offers/active')
      .then(res=>{
        console.log("OFFERS →", res.data);
        setOffers(res.data);
      })
      .catch(console.error)
  },[])
  

  return (
    <div className="container mt-4">
      <h3>Today's Discounts</h3>
      <div className="row">
        {offers.map(o=> (
          <div className="col-md-4" key={o.id}>
            <div className="card mb-3">
              <div className="card-body">
                <p>
                  Shop Status:
                  {o.shopOpen
                    ? <span className="badge bg-success ms-2">OPEN</span>
                    : <span className="badge bg-danger ms-2">CLOSED</span>
                  }
                </p>

                <h5>{o.dealName}</h5>
                <p>Item: {o.itemName || '—'}</p>
                <p>Discount: {o.discountValue} {o.discountType}</p>
                <p>Valid: {new Date(o.startTime).toLocaleString()} — {new Date(o.endTime).toLocaleString()}</p>
                <button
                  className="btn btn-success"
                  disabled={!o.shopOpen}
                  onClick={() => openBooking(o)}
                >
                  Book
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>

      {showModal && (
        <BookingModal offer={selectedOffer} onClose={()=>setShowModal(false)} />
      )}
    </div>
  )
}