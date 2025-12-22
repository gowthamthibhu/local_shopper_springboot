import { Routes, Route, Navigate } from 'react-router-dom'
import Login from './pages/Login'
import CustomerHome from './pages/CustomerHome'
import OwnerHome from './pages/OwnerHome'
import ShopCreate from './pages/ShopCreate'
import ItemCreate from './pages/ItemCreate'
import OfferCreate from './pages/OfferCreate'
import PickupSlotCreate from './pages/PickupSlotCreate'
import Navbar from './components/Navbar'

function RequireAuth({ children, roles }) {
  const role = localStorage.getItem('role')
  if (!role) return <Navigate to="/" />
  if (roles && !roles.includes(role)) return <Navigate to="/" />
  return children
}

export default function App() {
  return (
    <>
      {/* ✅ Navbar is now INSIDE Router context */}
      <Navbar />

      <Routes>
        <Route path="/" element={<Login />} />

        <Route
          path="/customer"
          element={
            <RequireAuth roles={['CUSTOMER']}>
              <CustomerHome />
            </RequireAuth>
          }
        />

        <Route
          path="/owner"
          element={
            <RequireAuth roles={['SHOP_OWNER']}>
              <OwnerHome />
            </RequireAuth>
          }
        />

        <Route
          path="/owner/create-shop"
          element={
            <RequireAuth roles={['SHOP_OWNER']}>
              <ShopCreate />
            </RequireAuth>
          }
        />

        <Route
          path="/owner/create-item"
          element={
            <RequireAuth roles={['SHOP_OWNER']}>
              <ItemCreate />
            </RequireAuth>
          }
        />

        <Route
          path="/owner/create-offer"
          element={
            <RequireAuth roles={['SHOP_OWNER']}>
              <OfferCreate />
            </RequireAuth>
          }
        />

        <Route
          path="/owner/create-slot"
          element={
            <RequireAuth roles={['SHOP_OWNER']}>
              <PickupSlotCreate />
            </RequireAuth>
          }
        />
      </Routes>
    </>
  )
}
