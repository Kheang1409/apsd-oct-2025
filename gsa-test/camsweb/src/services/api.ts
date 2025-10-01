import axios from "axios";
import { Account } from "../types";

const API_BASE_URL = process.env.REACT_APP_API_URL || "http://localhost:5134";

const api = axios.create({
  baseURL: `${API_BASE_URL}/api`,
  headers: {
    "Content-Type": "application/json",
  },
});

export const accountService = {
  // Get all accounts
  getAllAccounts: async (): Promise<Account[]> => {
    try {
      const response = await api.get<Account[]>("/account/list");
      return response.data;
    } catch (error) {
      console.error("Error fetching accounts:", error);
      throw error;
    }
  },

  // Get prime accounts
  getPrimeAccounts: async (): Promise<Account[]> => {
    try {
      const response = await api.get<Account[]>("/account/prime/list");
      return response.data;
    } catch (error) {
      console.error("Error fetching prime accounts:", error);
      throw error;
    }
  },
};

export default api;
