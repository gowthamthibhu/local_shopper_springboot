import { useParams } from "react-router-dom"
import { useEffect, useState } from "react"
import api from "../../../api/api"
import BookingModal from "../Bookings/BookingModal"

export default function ShopPage(){

  const { shopId } = useParams()
  const [offers, setOffers] = useState([])
  const [shop, setShop] = useState(null)
  const [selectedOffer, setSelectedOffer] = useState(null)

  useEffect(()=>{

    api.get(`/shops/${shopId}`)
      .then(res => setShop(res.data))

    api.get(`/offers/shop/${shopId}`)
      .then(res => setOffers(res.data))

  },[shopId])

  return (
    <div className="container mt-4">

      {shop && (
        <>
          <h3>{shop.shopName}</h3>
          <h6>{shop.area}, {shop.city}</h6>
          <hr />
        </>
      )}

      <h4>Available Offers</h4>

      <div className="row">

        {offers.length === 0 && (
          <p>No active offers right now.</p>
        )}

        {offers.map(o => (
          <div className="col-md-4" key={o.id}>
            <div className="card mb-3">
              <div className="card-body">

                <h5>{o.dealName}</h5>
                <p>Item: {o.itemName}</p>
                <p>Discount: {o.discountValue} {o.discountType}</p>

                <button
                  className="btn btn-success"
                  onClick={() => setSelectedOffer(o)}
                >
                  Book
                </button>

              </div>
            </div>
          </div>
        ))}

      </div>

      {selectedOffer && (
        <BookingModal
          offer={selectedOffer}
          onClose={() => setSelectedOffer(null)}
        />
      )}

    </div>
  )
}
