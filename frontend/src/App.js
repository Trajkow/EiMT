import React from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import Layout from './components/layout/Layout';
import ProtectedRoute from './components/shared/ProtectedRoute';

import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import AccommodationsPage from './pages/AccommodationsPage';
import HostsPage from './pages/HostsPage';
import CountriesPage from './pages/CountriesPage';
import ReservationsPage from './pages/ReservationsPage';

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route element={<Layout />}>
            <Route path="/" element={<HomePage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />

            <Route
              path="/reservations"
              element={
                <ProtectedRoute>
                  <ReservationsPage />
                </ProtectedRoute>
              }
            />
            <Route
              path="/accommodations"
              element={
                <ProtectedRoute>
                  <AccommodationsPage />
                </ProtectedRoute>
              }
            />
            <Route
              path="/hosts"
              element={
                <ProtectedRoute>
                  <HostsPage />
                </ProtectedRoute>
              }
            />
            <Route
              path="/countries"
              element={
                <ProtectedRoute>
                  <CountriesPage />
                </ProtectedRoute>
              }
            />

            <Route path="*" element={<Navigate to="/" replace />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
