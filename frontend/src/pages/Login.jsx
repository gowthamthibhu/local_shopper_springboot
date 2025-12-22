import { useState } from 'react'
import api from '../api/api'
import { useNavigate } from 'react-router-dom'

export default function Login(){
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const navigate = useNavigate()

  async function handleLogin(e){
    e.preventDefault()
    try{
        const res = await api.post("/auth/login", {
            email: email.trim(),
            password: password.trim()
        });
      const { userId, name, role } = res.data
      localStorage.setItem('userId', userId)
      localStorage.setItem('name', name)
      localStorage.setItem('role', role)
      if(role === 'CUSTOMER') navigate('/customer')
      else navigate('/owner')
    }catch(err){
      alert('Invalid credentials')
      console.error(err?.response?.data || err.message)
    }
  }

  return (
    <div className="container mt-5 col-md-4">
      <h3 className="mb-3">Login</h3>
      <form onSubmit={handleLogin}>
        <input className="form-control mb-2" placeholder="Email" value={email} onChange={e=>setEmail(e.target.value)} />
        <input className="form-control mb-2" type="password" placeholder="Password" value={password} onChange={e=>setPassword(e.target.value)} />
        <button className="btn btn-primary w-100">Login</button>
      </form>
    </div>
  )
}