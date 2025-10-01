export interface Customer {
  customerId: number;
  firstName: string;
  lastName: string;
}

export interface Account {
  accountId: number;
  accountNumber: string;
  accountType: string;
  dateOpened: string;
  balance: number;
  ownerId: number;
  owner: Customer;
}

export interface ApiResponse<T> {
  data: T;
  message?: string;
  success: boolean;
}
