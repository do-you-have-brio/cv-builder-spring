import axios from "axios";

export const authServerInstance = axios.create({
  baseURL: import.meta.env.VITE_AUTH_SERVER_URL,
});

export const apiServerInstance = axios.create({
  baseURL: import.meta.env.VITE_API_SERVER_URL,
});
