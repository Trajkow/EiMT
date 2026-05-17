import React from 'react';
import { Outlet } from 'react-router-dom';
import { Box } from '@mui/material';
import Header from './Header';
import Footer from './Footer';

const Layout = () => (
  <Box sx={{ display: 'flex', flexDirection: 'column', minHeight: '100vh', bgcolor: '#f8fafc' }}>
    <Header />
    <Box component="main" sx={{ flexGrow: 1, py: 4, px: { xs: 2, md: 4 }, maxWidth: 1280, mx: 'auto', width: '100%' }}>
      <Outlet />
    </Box>
    <Footer />
  </Box>
);

export default Layout;
