import { useEffect, useState } from "react";
import api from "../../../api/api";

export default function OwnerMyShops() {

  const [shops, setShops] = useState([]);

  const ownerId = localStorage.getItem("userId");

  // load shops
  useEffect(() => {
    let cancelled = false;

    (async () => {
      const res = await api.get(`/shops/my`, {
        params: { ownerId }
      });

      if (!cancelled) setShops(res.data);
    })();

    return () => {
      cancelled = true;
    };
  }, [ownerId]);

  // toggle open/closed
  async function toggleOpen(shop) {
    await api.patch(`/shops/${shop.id}/open`, null, {
      params: { open: !shop.open }
    });

    // reload list after update
    const res = await api.get(`/shops/my`, { params:{ ownerId }});
    setShops(res.data);
  }

  return (
    <div className="container mt-4">
      <h3>My Shops</h3>

      <ul className="list-group">
        {shops.map(shop => (
          <li key={shop.id} className="list-group-item d-flex justify-content-between">

            <div>
              <b>{shop.shopName}</b><br/>
              {shop.address}, {shop.area}, {shop.city}
              <br/>

              {shop.open
                ? <span className="badge bg-success">OPEN</span>
                : <span className="badge bg-danger">CLOSED</span>
              }

              <br/>
              <small>
                {shop.openingTime} — {shop.closingTime}
              </small>
            </div>

            <button
              className="btn btn-outline-primary"
              onClick={()=>toggleOpen(shop)}
            >
              {shop.open ? "Close Shop" : "Open Shop"}
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}
