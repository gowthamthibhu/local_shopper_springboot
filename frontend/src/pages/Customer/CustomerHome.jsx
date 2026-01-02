import { useEffect, useState } from "react"
import api from "../../api/api"
import { Link } from "react-router-dom"

export default function CustomerHome(){

  const [shops, setShops] = useState([])

  useEffect(()=>{
    api.get("/shops")
      .then(res => setShops(res.data))
      .catch(console.error)
  },[])

  return (
    <div className="container mt-4">

      <h3>Nearby Shops</h3>

      <div className="row">
        {shops.map(shop => (
          <div className="col-md-4" key={shop.id}>
            <div className="card mb-3">
              <div className="card-body">

                <h5>{shop.shopName}</h5>

                <p>{shop.area}, {shop.city}</p>

                <p>
                  Status:
                  {shop.open
                    ? <span className="badge bg-success ms-2">OPEN</span>
                    : <span className="badge bg-danger ms-2">CLOSED</span>
                  }
                </p>

                {shop.open ? (
                    <Link
                      className="btn btn-primary"
                      to={`/shop/${shop.id}`}
                    >
                      View Offers
                    </Link>
                  ) : (
                    <button className="btn btn-secondary" disabled>
                      Shop Closed
                    </button>
                  )}
              </div>
            </div>
          </div>
        ))}
      </div>

    </div>
  )
}
