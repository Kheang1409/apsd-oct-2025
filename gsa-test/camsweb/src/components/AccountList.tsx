import React from "react";
import { Table, Card, Badge, Alert } from "react-bootstrap";
import { Account } from "../types";

interface AccountListProps {
  accounts: Account[];
  loading: boolean;
  error: string | null;
  title: string;
}

const AccountList: React.FC<AccountListProps> = ({
  accounts,
  loading,
  error,
  title,
}) => {
  const formatCurrency = (amount: number): string => {
    return new Intl.NumberFormat("en-US", {
      style: "currency",
      currency: "USD",
    }).format(amount);
  };

  const formatDate = (dateString: string): string => {
    return new Date(dateString).toLocaleDateString("en-US", {
      year: "numeric",
      month: "short",
      day: "numeric",
    });
  };

  const getAccountTypeBadge = (accountType: string) => {
    const type = accountType.toLowerCase();
    let variant = "secondary";

    if (type.includes("checking")) variant = "primary";
    else if (type.includes("savings")) variant = "success";
    else if (type.includes("credit")) variant = "warning";
    else if (type.includes("loan")) variant = "danger";

    return <Badge bg={variant}>{accountType}</Badge>;
  };

  if (loading) {
    return (
      <div
        className="d-flex justify-content-center align-items-center"
        style={{ minHeight: "200px" }}
      >
        <div className="spinner-border" role="status">
          <span className="visually-hidden">Loading...</span>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <Alert variant="danger">
        <Alert.Heading>Error!</Alert.Heading>
        <p>{error}</p>
      </Alert>
    );
  }

  return (
    <Card>
      <Card.Header>
        <h4 className="mb-0">{title}</h4>
        <small className="text-muted">{accounts.length} account(s) found</small>
      </Card.Header>
      <Card.Body className="p-0">
        {accounts.length === 0 ? (
          <div className="text-center p-4">
            <p className="text-muted">No accounts found.</p>
          </div>
        ) : (
          <Table responsive striped hover className="mb-0">
            <thead className="table-dark">
              <tr>
                <th>Account #</th>
                <th>Account Type</th>
                <th>Customer Name</th>
                <th>Date Opened</th>
                <th>Balance</th>
              </tr>
            </thead>
            <tbody>
              {accounts.map((account) => (
                <tr key={account.accountId}>
                  <td>
                    <code>{account.accountNumber}</code>
                  </td>
                  <td>{getAccountTypeBadge(account.accountType)}</td>
                  <td>
                    <strong>
                      {account.owner.firstName} {account.owner.lastName}
                    </strong>
                    <br />
                    <small className="text-muted">
                      ID: {account.owner.customerId}
                    </small>
                  </td>
                  <td>{formatDate(account.dateOpened)}</td>
                  <td>
                    <span
                      className={
                        account.balance >= 0 ? "text-success" : "text-danger"
                      }
                    >
                      {formatCurrency(account.balance)}
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        )}
      </Card.Body>
    </Card>
  );
};

export default AccountList;
