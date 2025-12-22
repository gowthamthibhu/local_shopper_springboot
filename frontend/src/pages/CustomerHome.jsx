import { useEffect, useState } from "react";
import api from "../api/api";

function CustomerHome() {
  const [offers, setOffers] = useState([]);

  useEffect(() => {
    api.get("/offers/active").then((res) => setOffers(res.data));
  }, []);

  return (
    <div className="container mt-4">
      <h3>Today's Discounts</h3>

      <div className="row">
        {offers.map((o) => (
          <div className="col-md-4" key={o.id}>
            <div className="card mb-3">
              <div className="card-body">
                <h5>{o.dealName}</h5>
                <p>Discount: {o.discountValue}</p>
                <button className="btn btn-success">Book</button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default CustomerHome;
