import React from "react";
import { Navbar, Nav, Container } from "react-bootstrap";

interface HeaderProps {
  onViewChange: (view: "home" | "all" | "prime") => void;
  currentView: "home" | "all" | "prime";
}

const Header: React.FC<HeaderProps> = ({ onViewChange, currentView }) => {
  return (
    <Navbar bg="dark" variant="dark" expand="lg">
      <Container>
        <Navbar.Brand href="#home">
          <strong>CAMS Web</strong> - Customer Account Management System
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ms-auto">
            <Nav.Link
              href="#home"
              onClick={(e) => {
                e.preventDefault();
                onViewChange("home");
              }}
              className={currentView === "home" ? "active" : ""}
            >
              Home
            </Nav.Link>
            <Nav.Link
              href="#all"
              onClick={(e) => {
                e.preventDefault();
                onViewChange("all");
              }}
              className={currentView === "all" ? "active" : ""}
            >
              All Accounts
            </Nav.Link>
            <Nav.Link
              href="#prime"
              onClick={(e) => {
                e.preventDefault();
                onViewChange("prime");
              }}
              className={currentView === "prime" ? "active" : ""}
            >
              Prime Accounts
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default Header;
