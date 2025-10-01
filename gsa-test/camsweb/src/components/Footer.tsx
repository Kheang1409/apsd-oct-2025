import React from "react";
import { Container, Row, Col, Alert } from "react-bootstrap";

const Footer: React.FC = () => {
  return (
    <footer className="bg-light mt-5 py-4">
      <Container>
        <Row>
          <Col md={8}>
            <h6>CAMS Web - Customer Account Management System</h6>
            <p className="text-muted mb-0">
              A modern web application for managing customer accounts and
              financial data.
            </p>
          </Col>
          <Col md={4} className="text-md-end">
            <small className="text-muted">
              © 2025 CAMS Web. Built with React & TypeScript.
            </small>
          </Col>
        </Row>
        <hr className="my-3" />
        <Row>
          <Col>
            <Alert variant="info" className="mb-0">
              <small>
                <strong>API Status:</strong> Connected to backend at{" "}
                <code>
                  {process.env.REACT_APP_API_URL || "http://localhost:5134"}
                </code>
              </small>
            </Alert>
          </Col>
        </Row>
      </Container>
    </footer>
  );
};

export default Footer;
