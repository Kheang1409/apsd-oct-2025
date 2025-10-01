import React, { useState, useEffect } from "react";
import { Container, Row, Col, Alert, Button } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import Header from "./components/Header";
import AccountList from "./components/AccountList";
import Footer from "./components/Footer";
import Home from "./components/Home";
import { accountService } from "./services/api";
import { Account } from "./types";

function App() {
  const [accounts, setAccounts] = useState<Account[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);
  const [currentView, setCurrentView] = useState<"home" | "all" | "prime">(
    "home"
  );

  const fetchAccounts = async (isPrime: boolean = false) => {
    setLoading(true);
    setError(null);

    try {
      const data = isPrime
        ? await accountService.getPrimeAccounts()
        : await accountService.getAllAccounts();
      setAccounts(data);
    } catch (err: any) {
      const errorMessage =
        err.response?.data?.message ||
        err.message ||
        "Failed to fetch accounts";
      setError(errorMessage);
      console.error("Error fetching accounts:", err);
    } finally {
      setLoading(false);
    }
  };

  const handleViewChange = (view: "home" | "all" | "prime") => {
    setCurrentView(view);
    if (view !== "home") {
      fetchAccounts(view === "prime");
    }
  };

  const handleRefresh = () => {
    fetchAccounts(currentView === "prime");
  };

  useEffect(() => {
    // Don't fetch accounts on initial load since we start with home view
  }, []);

  const getTitle = () => {
    return currentView === "prime" ? "Prime Accounts" : "All Accounts";
  };

  return (
    <div className="App">
      <Header onViewChange={handleViewChange} currentView={currentView} />

      {currentView === "home" ? (
        <Home />
      ) : (
        <Container className="mt-4">
          <Row>
            <Col>
              <div className="d-flex justify-content-between align-items-center mb-3">
                <h2>Customer Account Management</h2>
                <Button
                  variant="outline-secondary"
                  onClick={handleRefresh}
                  disabled={loading}
                >
                  {loading ? (
                    <>
                      <span
                        className="spinner-border spinner-border-sm me-2"
                        role="status"
                        aria-hidden="true"
                      ></span>
                      Refreshing...
                    </>
                  ) : (
                    <>
                      <i className="bi bi-arrow-clockwise me-2"></i>
                      Refresh
                    </>
                  )}
                </Button>
              </div>

              {error && (
                <Alert
                  variant="warning"
                  dismissible
                  onClose={() => setError(null)}
                >
                  <Alert.Heading>Connection Issue</Alert.Heading>
                  <p>{error}</p>
                  <p className="mb-0">
                    <strong>Note:</strong> Make sure your CAMS backend is
                    running on{" "}
                    <code>
                      {process.env.REACT_APP_API_URL || "http://localhost:5134"}
                    </code>
                  </p>
                </Alert>
              )}

              <AccountList
                accounts={accounts}
                loading={loading}
                error={error}
                title={getTitle()}
              />
            </Col>
          </Row>
        </Container>
      )}

      <Footer />
    </div>
  );
}

export default App;
