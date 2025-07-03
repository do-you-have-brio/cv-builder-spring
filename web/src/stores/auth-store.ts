import { create } from "zustand";
import { createJSONStorage, persist } from "zustand/middleware";

export interface AuthStore {
  session: boolean;
  setSession: (session: boolean) => void;
  clearSession: () => void;
}

export const useAuthStore = create<AuthStore>()(
  persist(
    (set) => ({
      session: false,

      setSession: (session: boolean) => set(() => ({ session })),
      clearSession: () => set(() => ({ session: false })),
    }),
    {
      name: "@cv-builder::auth",
      storage: createJSONStorage(() => localStorage),
      version: 1,
    }
  )
);
