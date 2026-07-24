import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from "path";
import fs from "fs";
import dotenv from "dotenv";

export default ({ command, mode }) => {
	let envFiles = [`.env`, `.env.${mode}`];
	for (const file of envFiles) {
		const envConfig = dotenv.parse(fs.readFileSync(file))
		for (const k in envConfig) {
			process.env[k] = envConfig[k]
		}
	}
	return defineConfig({
		plugins: [vue()],
		resolve: {
			alias: {
				'@': path.resolve(__dirname, 'src')
			}
		},
		logLevel: mode == "development" ? "info" : "warn",
		define: {
			'process.env': process.env,
			__VUE_PROD_HYDRATION_MISMATCH_DETAILS__: 'true'
		},
		// MapLibre GL JS v6 使用了 BigInt（Safari 14+ 才支持），提升 esbuild 目标
		build: {
			target: 'es2022',
		},
		optimizeDeps: {
			include: ['maplibre-gl'],
			esbuildOptions: {
				target: 'es2022',
			},
		},
		esbuild: {
			target: 'es2022',
		},
		server: {
			host: "0.0.0.0",
			port: 5173,
			strictPort: true,
			open: true,
			cors: true,
			fs: { strict: true }
		},
	})
}