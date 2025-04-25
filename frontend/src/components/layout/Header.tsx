import { FiUser } from "react-icons/fi";
import Link from "next/link";

export default function Header() {
  return (
    <header className="bg-[#212121] text-white flex items-center justify-between px-6 py-4 border-b border-gray-500 w-full">
      <div className="flex-1"></div>
      <h1 className="text-2xl font-bold text-center flex-1">Dev Interview</h1>
      <div className="flex items-center gap-4 flex-1 justify-end">
        <Link href="/create">
          <button className="border border-white px-3 py-1 rounded">
            create
          </button>
        </Link>
        <FiUser size={24} />
      </div>
    </header>
  );
}
