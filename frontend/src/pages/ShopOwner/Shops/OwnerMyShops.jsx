import { useEffect, useState } from "react";
import api from "../../../api/api";

export default function OwnerMyShops() {

  const [shops, setShops] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const ownerId = localStorage.getItem("userId");

  useEffect(() => {
    async function fetchShops() {
      try {
        const res = await api.get(`/shops/my`, {
          params: { ownerId }
        });

        setShops(res.data);
      } catch (err) {
        setError(err?.response?.data || "Failed to load shops");
      } finally {
        setLoading(false);
      }
    }

    fetchShops();
  }, [ownerId]);

  if (loading) return <div className="container mt-4">Loading...</div>;
  if (error) return <div className="container mt-4 text-danger">{error}</div>;

  return (
    <div className="container mt-4">
      <h3>My Shops</h3>

      {shops.length === 0 && <p>You have not created any shops yet.</p>}

      <ul className="list-group">
        {shops.map(shop => (
          <li key={shop.id} className="list-group-item">
            <b>{shop.shopName}</b>
            <br />
            {shop.address}, {shop.area}, {shop.city}
          </li>
        ))}
      </ul>
    </div>
  );
}
