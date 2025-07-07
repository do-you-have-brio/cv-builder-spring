import type { SessionSchema } from "@/@types/schemas/auth";
import { create } from "zustand";
import { createJSONStorage, persist } from "zustand/middleware";

export interface AuthStore {
  session: SessionSchema | null;
  setSession: (session: SessionSchema | null) => void;
  clearSession: () => void;
}

export const useAuthStore = create<AuthStore>()(
  persist(
    (set) => ({
      session: null,

      setSession: (session: SessionSchema | null) => set(() => ({ session })),
      clearSession: () => set(() => ({ session: null })),
    }),
    {
      name: "@cv-builder::auth",
      storage: createJSONStorage(() => localStorage),
      version: 1,
    }
  )
);
