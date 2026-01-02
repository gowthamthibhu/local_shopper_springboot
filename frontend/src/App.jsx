import { Routes, Route, Navigate } from 'react-router-dom'
import Login from './pages/Login'
import CustomerHome from './pages/Customer/CustomerHome'
import OwnerHome from './pages/ShopOwner/OwnerHome'
import ShopCreate from './pages/ShopOwner/Shops/ShopCreate'
import ItemCreate from './pages/ShopOwner/Items/ItemCreate'
import OfferCreate from './pages/ShopOwner/Offers/OfferCreate'
import PickupSlotCreate from './pages/ShopOwner/Pickups/PickupSlotCreate'
import Navbar from './components/Navbar'
import OwnerMyShops from "./pages/ShopOwner/Shops/OwnerMyShops";
import AutoRedirect from "./pages/AutoRedirect";
import ShopPage from './pages/Customer/ShopPage/ShopPage'


function RequireAuth({ children, roles }) {
  const role = localStorage.getItem('role')
  if (!role) return <Navigate to="/login" />
  if (roles && !roles.includes(role)) return <Navigate to="/" />
  return children
}

export default function App() {
  return (
    <>
      <Navbar />

      <Routes>
        <Route path="/" element={<AutoRedirect />} />
        <Route path="/login" element={<Login />} />

        <Route
          path="/customer"
          element={
            <RequireAuth roles={['CUSTOMER']}>
              <CustomerHome />
            </RequireAuth>
          }
        />

        <Route
          path="/shop/:shopId"
          element={
            <RequireAuth roles={['CUSTOMER']}>
              <ShopPage />
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
          path="/owner/my-shops" 
          element={
            <RequireAuth roles={['SHOP_OWNER']}>
              <OwnerMyShops />
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
