import React, { useState } from "react";
import Sidebar from "./Sidebar";
import Header from "./Header";
import { FiMenu } from "react-icons/fi";

export default function Layout({ children }: { children: React.ReactNode }) {
  const [isSidebarOpen, setIsSidebarOpen] = useState(true);

  return (
    <div className="flex h-screen bg-[#212121]">
      {/* 🔥 왼쪽 상단 고정 버튼 */}
      <button
        onClick={() => setIsSidebarOpen(!isSidebarOpen)}
        className="fixed top-4 left-4 z-50 text-white"
      >
        <FiMenu size={24} />
      </button>

      {/* 🔥 사이드바 */}
      {isSidebarOpen && <Sidebar />}

      {/* 🔥 오른쪽 영역 */}
      <div className="flex flex-col flex-1 transition-all duration-300">
        <Header />
        <main className="p-6">{children}</main>
      </div>
    </div>
  );
}
